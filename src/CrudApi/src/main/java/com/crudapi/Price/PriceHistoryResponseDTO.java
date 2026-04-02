package com.crudapi.Price;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PriceHistoryResponseDTO {
    
    private Long productId;
    private String productName;
    private BigDecimal currentPrice;
    private LocalDate lastUpdate;
    private List<MonthlySummaryDTO> history;
    
    public PriceHistoryResponseDTO() {
    }
        
    public PriceHistoryResponseDTO(Long productId, String productName, 
                                   BigDecimal currentPrice, LocalDate lastUpdate,
                                   List<MonthlySummaryDTO> history) {
        this.productId = productId;
        this.productName = productName;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
        this.history = history;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
    
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
    
    public LocalDate getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public List<MonthlySummaryDTO> getHistory() {
        return history;
    }
    
    public void setHistory(List<MonthlySummaryDTO> history) {
        this.history = history;
    }
    
    // Classe para o resumo mensal (com dados diários)
    public static class MonthlySummaryDTO {
        
        private String month;
        private int year;
        private BigDecimal lowestPrice;
        private BigDecimal highestPrice;
        private BigDecimal averagePrice;
        private BigDecimal lastPriceOfMonth;
        private BigDecimal variationFromPreviousMonth;
        private Map<LocalDate, BigDecimal> dailyPrices;  // Dia → Preço
        
        public MonthlySummaryDTO() {
        }
        
        // Getters e Setters
        public String getMonth() {
            return month;
        }
        
        public void setMonth(String month) {
            this.month = month;
        }
        
        public int getYear() {
            return year;
        }
        
        public void setYear(int year) {
            this.year = year;
        }
        
        public BigDecimal getLowestPrice() {
            return lowestPrice;
        }
        
        public void setLowestPrice(BigDecimal lowestPrice) {
            this.lowestPrice = lowestPrice;
        }
        
        public BigDecimal getHighestPrice() {
            return highestPrice;
        }
        
        public void setHighestPrice(BigDecimal highestPrice) {
            this.highestPrice = highestPrice;
        }
        
        public BigDecimal getAveragePrice() {
            return averagePrice;
        }
        
        public void setAveragePrice(BigDecimal averagePrice) {
            this.averagePrice = averagePrice;
        }
        
        public BigDecimal getLastPriceOfMonth() {
            return lastPriceOfMonth;
        }
        
        public void setLastPriceOfMonth(BigDecimal lastPriceOfMonth) {
            this.lastPriceOfMonth = lastPriceOfMonth;
        }
        
        public BigDecimal getVariationFromPreviousMonth() {
            return variationFromPreviousMonth;
        }
        
        public void setVariationFromPreviousMonth(BigDecimal variationFromPreviousMonth) {
            this.variationFromPreviousMonth = variationFromPreviousMonth;
        }
        
        public Map<LocalDate, BigDecimal> getDailyPrices() {
            return dailyPrices;
        }
        
        public void setDailyPrices(Map<LocalDate, BigDecimal> dailyPrices) {
            this.dailyPrices = dailyPrices;
        }
    }
}
