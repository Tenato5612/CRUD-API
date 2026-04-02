package com.crudapi.Price;

import com.crudapi.Product.ProductEntity;
import com.crudapi.Product.ProductRepository;
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

@Service
public class PriceService {
    
    private final PriceRepository priceRepository;        
    private final ProductRepository productRepository;
    
    public PriceService(PriceRepository priceRepository,
                        ProductRepository productRepository){
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;        
    }
    
    public PriceResponseDTO create(PriceCreateDTO dto){        
        PriceEntity entity = new PriceEntity();
        ProductEntity productEntity = new ProductEntity();
        if(dto == null){
            throw new IllegalArgumentException("DTO cannot be null");
        }

        if(dto.getValue() == null){
            throw new IllegalArgumentException("Price value cannot be null");
        }
        
        productEntity = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        PriceEntity lastPrice = priceRepository.findTopByProductOrderByCreateAtDesc(productEntity);

        if(lastPrice != null && lastPrice.getValue().compareTo(dto.getValue()) == 0){
            return new PriceResponseDTO(lastPrice);
        }

        entity.setProductId(productEntity);
        entity.setValue(dto.getValue());
        
        LocalDateTime collectedAt = dto.getCreateAt() != null ? dto.getCreateAt() : LocalDateTime.now();
        entity.setCreateAt(collectedAt);
        
        entity.setCreateAt(LocalDateTime.now());                    

        if (dto.getValue().compareTo(BigDecimal.ZERO) > 0) {
            productEntity.setStatus(ProductEntity.Status.Active);
        } else {
            productEntity.setStatus(ProductEntity.Status.Disable);
        }
        
        priceRepository.save(entity);

        productEntity.setPrice(dto.getValue());
        productRepository.save(productEntity);

        return new PriceResponseDTO(entity);
    }        
    
    public PriceResponseDTO getLastPriceByProduct(Long id){
        ProductEntity productEntity = new ProductEntity();
        productEntity = productRepository.findById(id)
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
        
        List<PriceEntity> prices = priceRepository.findByProductOrderByCreatedAtAsc(product);
        
        return prices.stream()
                .map(PriceResponseDTO::new)
                .toList();
    }
    
    public PriceHistoryResponseDTO getPriceById(Long priceId) {
        ProductEntity product = productRepository.findById(priceId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + priceId));

        PriceEntity lastPrice = priceRepository.findTopByProductOrderByCreateAtDesc(product);
        
        if (lastPrice == null) {
            throw new RuntimeException("No price found for product id: " + priceId);
        }
        
        return new PriceResponseDTO(lastPrice);
        
    }    
    
    public void deletePrice(Long priceId) {
        // ✅ CORREÇÃO 14: Método de deleção
        // Motivo: Necessário para correções e manutenção
        
        if (!priceRepository.existsById(priceId)) {
            throw new RuntimeException("Price not found with id: " + priceId);
        }
        priceRepository.deleteById(priceId);
    }
    
    public PriceHistoryResponseDTO getProductPriceHistory(Long productId) {
        // ✅ CORREÇÃO 15: Método corrigido para usar a lista correta de preços
        // Antes: usava findTopByProduct... (retorna UM preço)
        // Agora: usa findByProductOrderByCollectedAtAsc (retorna LISTA de preços)
        
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        List<PriceEntity> prices = priceRepository.findByProductOrderByCreatedAtAsc(product);
        
        if (prices.isEmpty()) {
            PriceHistoryResponseDTO emptyHistory = new PriceHistoryResponseDTO();
            emptyHistory.setProductId(product.getId());
            emptyHistory.setProductName(product.getName());
            emptyHistory.setCurrentPrice(product.getPrice());
            emptyHistory.setLastUpdate(null);
            emptyHistory.setHistory(new ArrayList<>());
            return emptyHistory;
        }
        
        // Agrupa preços por mês/ano
        Map<String, List<PriceEntity>> groupedByMonth = groupPricesByMonth(prices);
        
        // Constrói resumo para cada mês
        List<PriceHistoryResponseDTO.MonthlySummaryDTO> history = new ArrayList<>();
        for (Map.Entry<String, List<PriceEntity>> entry : groupedByMonth.entrySet()) {
            PriceHistoryResponseDTO.MonthlySummaryDTO summary = buildMonthlySummary(entry.getKey(), entry.getValue());
            history.add(summary);
        }
        
        // Calcula variação entre meses consecutivos
        calculateVariationBetweenMonths(history);
        
        // Monta resposta
        PriceHistoryResponseDTO response = new PriceHistoryResponseDTO();
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setCurrentPrice(product.getPrice());
        
        // ✅ CORREÇÃO 16: LastUpdate é a data do último preço coletado
        LocalDate lastUpdate = prices.get(prices.size() - 1).getCreateAt().toLocalDate();
        response.setLastUpdate(lastUpdate);
        response.setHistory(history);
        
        return response;
    }
    
    private void validateCreateDTO(PriceCreateDTO dto) {
        // ✅ CORREÇÃO 17: Validações centralizadas
        if (dto == null) {
            throw new IllegalArgumentException("PriceCreateDTO cannot be null");
        }
        if (dto.getProductId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (dto.getValue() == null) {
            throw new IllegalArgumentException("Price value cannot be null");
        }
        if (dto.getValue().compareTo(BigDecimal.ZERO) <= 0) {
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
            "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO",
            "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"
        };
        return months[monthNumber - 1];
    }
    
    private PriceHistoryResponseDTO.MonthlySummaryDTO buildMonthlySummary(String monthKey, List<PriceEntity> prices) {
        prices.sort(Comparator.comparing(PriceEntity::getCreateAt));  
        String[] parts = monthKey.split("-");
        int year = Integer.parseInt(parts[0]);
        String month = getMonthName(Integer.parseInt(parts[1]));

        BigDecimal lowestPrice = prices.stream()
                .map(PriceEntity::getValue)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        BigDecimal highestPrice = prices.stream()
                .map(PriceEntity::getValue)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        BigDecimal averagePrice = prices.stream()
                .map(PriceEntity::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(prices.size()), 2, RoundingMode.HALF_UP);

        BigDecimal lastPriceOfMonth = prices.get(prices.size() - 1).getValue();
        Map<LocalDate, BigDecimal> dailyPrices = new LinkedHashMap<>();
        for (PriceEntity price : prices) {
            LocalDate date = price.getCreateAt().toLocalDate();
            dailyPrices.put(date, price.getValue()); // Substitui pelo mais recente
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
