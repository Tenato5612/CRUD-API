package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Product.ProductRepository;
import com.crudapi.Product.ProductService;
import com.crudapi.Product.ProductUpdateDTO;
import com.crudapi.Scrap.ScrapEntity;
import com.crudapi.Scrap.ScrapRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceService {
    
    private final PriceRepository priceRepository;        
    private final ProductRepository productRepository;
    private final ScrapRepository scrapRepository;
    
    public PriceService(PriceRepository priceRepository,
                        ProductRepository productRepository,
                        ScrapRepository scrapRepository1){
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;        
        this.scrapRepository = scrapRepository1;
    }
          
    @Transactional
    public PriceResponseDTO create(PriceCreateDTO dto){          
        validateCreateDTO(dto);                       
        
        PriceEntity entity;                            
        entity = dto.toEntity();  
        ProductService productService;
        ProductUpdateDTO productUpdateDTO;
        
        ProductEntity productEntity = productRepository.findById(dto.getProduct())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProduct()));                               
        
        ScrapEntity scrapEntity = scrapRepository.findById(dto.getScrapId()).orElseThrow(() -> new RuntimeException("Scrap not found"));                                                
//        PriceEntity lastPrice = priceRepository.findTopByProductOrderByCreateAtDesc(productEntity);
//        if(lastPrice != null && lastPrice.getPrice().compareTo(dto.getPrice()) == 0){
//            return new PriceResponseDTO(lastPrice);
//        }
        
        entity.setCreateAt(dto.getCreateAt() != null ? dto.getCreateAt() : LocalDateTime.now());        
        entity.setProduct(productEntity);        
        entity.setPrice(dto.getPrice());    
        productEntity.setPrice(entity.getPrice());
        
        productEntity.updateStatus();
                                
        priceRepository.save(entity);

        productRepository.save(productEntity);

        return new PriceResponseDTO(entity);
    }        
    
    public PriceResponseDTO lastPriceByProduct(Long id){        
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        PriceEntity last = priceRepository
                .findTopByProductOrderByCreateAtDesc(productEntity);
        
        if(last == null){
            throw new RuntimeException("No price found");
        }
        
        return new PriceResponseDTO(last);
    }           
        
    public List<PriceResponseDTO> listPricesByProduct(Long productId){
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        List<PriceEntity> prices = priceRepository.findByProductOrderByCreateAtAsc(product);
        
        return prices.stream()
                .map(PriceResponseDTO::new)
                .toList();
    }

    public PriceResponseDTO getPriceById(Long priceId){
        PriceEntity price = priceRepository.findById(priceId)
            .orElseThrow(() -> new RuntimeException("Price not found"));

        return new PriceResponseDTO(price);
    }
    
    public PriceHistoryResponseDTO getProductPriceHistory(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        List<PriceEntity> prices = priceRepository.findByProductOrderByCreateAtAsc(product);
        
        if (prices.isEmpty()) {
            PriceHistoryResponseDTO emptyHistory = new PriceHistoryResponseDTO();
            emptyHistory.setProductId(product.getId());
            emptyHistory.setProductName(product.getName());
            emptyHistory.setCurrentPrice(product.getPrice());
            emptyHistory.setLastUpdate(null);
            emptyHistory.setHistory(new ArrayList<>());
            return emptyHistory;
        }
        
        Map<String, List<PriceEntity>> groupedByMonth = groupPricesByMonth(prices);
        
        List<PriceHistoryResponseDTO.MonthlySummaryDTO> history = new ArrayList<>();
        for (Map.Entry<String, List<PriceEntity>> entry : groupedByMonth.entrySet()) {
            PriceHistoryResponseDTO.MonthlySummaryDTO summary = buildMonthlySummary(entry.getKey(), entry.getValue());
            history.add(summary);
        }
                
        calculateVariationBetweenMonths(history);
               
        PriceHistoryResponseDTO response = new PriceHistoryResponseDTO();
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setCurrentPrice(product.getPrice());
        
        LocalDate lastUpdate = prices.get(prices.size() - 1).getCreateAt().toLocalDate();
        response.setLastUpdate(lastUpdate);
        response.setHistory(history);
        
        return response;
    }
    
    private void validateCreateDTO(PriceCreateDTO dto) {        
        if (dto == null) {
            throw new IllegalArgumentException("PriceCreateDTO cannot be null");
        }
        if (dto.getProduct() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (dto.getPrice()== null) {
            throw new IllegalArgumentException("Price value cannot be null");
        }
        if (dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price value must be greater than zero");
        }
    }
    
    private Map<String, List<PriceEntity>> groupPricesByMonth(List<PriceEntity> prices) {
        Map<String, List<PriceEntity>> grouped = new LinkedHashMap<>();        
        for (PriceEntity price : prices) {
            String monthKey = price.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            if (!grouped.containsKey(monthKey)) {
                grouped.put(monthKey, new ArrayList<>());
            }
            grouped.get(monthKey).add(price);
        }
        return grouped;
    }
    
    private String getMonthName(int monthNumber) {
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        return months[monthNumber - 1];
    }
    
    private PriceHistoryResponseDTO.MonthlySummaryDTO buildMonthlySummary(String monthKey, List<PriceEntity> prices) {
        prices.sort(Comparator.comparing(PriceEntity::getCreateAt));  
        String[] parts = monthKey.split("-");
        int year = Integer.parseInt(parts[0]);
        String month = getMonthName(Integer.parseInt(parts[1]));

        BigDecimal lowestPrice = prices.stream()
                .map(PriceEntity::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        BigDecimal highestPrice = prices.stream()
                .map(PriceEntity::getPrice)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        BigDecimal averagePrice = prices.stream()
                .map(PriceEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(prices.size()), 2, RoundingMode.HALF_UP);

        BigDecimal lastPriceOfMonth = prices.get(prices.size() - 1).getPrice();
        Map<LocalDate, BigDecimal> dailyPrices = new LinkedHashMap<>();
        for (PriceEntity price : prices) {
            LocalDate date = price.getCreateAt().toLocalDate();
            dailyPrices.put(date, price.getPrice()); // Substitui pelo mais recente
        }

        PriceHistoryResponseDTO.MonthlySummaryDTO summary = new PriceHistoryResponseDTO.MonthlySummaryDTO();
        summary.setMonth(month);
        summary.setYear(year);
        summary.setLowestPrice(lowestPrice);
        summary.setHighestPrice(highestPrice);
        summary.setAveragePrice(averagePrice);
        summary.setLastPriceOfMonth(lastPriceOfMonth);
        summary.setDailyPrices(dailyPrices);
        summary.setVariationFromPreviousMonth(null);

        return summary;
    }
    
    private void calculateVariationBetweenMonths(List<PriceHistoryResponseDTO.MonthlySummaryDTO> history) {
        for (int i = 1; i < history.size(); i++) {
            BigDecimal previousLastPrice = history.get(i - 1).getLastPriceOfMonth();
            BigDecimal currentLastPrice = history.get(i).getLastPriceOfMonth();

            if (previousLastPrice != null && previousLastPrice.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal variation = currentLastPrice.subtract(previousLastPrice)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(previousLastPrice, 2, RoundingMode.HALF_UP);
                history.get(i).setVariationFromPreviousMonth(variation);
            } else {
                history.get(i).setVariationFromPreviousMonth(null);
            }
        }
    }    
}
