package tech.build.smartstock.service;

import org.springframework.stereotype.Service;
import tech.build.smartstock.domain.CsvStockItem;

@Service
public class PurchaseSectorService {

    private final AuthService authService;

    public PurchaseSectorService(AuthService authService) {
        this.authService = authService;
    }

    public boolean sendPurchaseRequest(CsvStockItem item, Integer purchaseQuantity) {

        // 1. autenticação na api para recuperar o token
        var token = authService.getToken();

        //  2. enviar solicitação de compra com o token gerado na chamada anterior

        return false;
    }
}
