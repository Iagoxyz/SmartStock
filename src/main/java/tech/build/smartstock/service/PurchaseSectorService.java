package tech.build.smartstock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.build.smartstock.client.PurchaseSectorClient;
import tech.build.smartstock.client.dto.PurchaseRequest;
import tech.build.smartstock.domain.CsvStockItem;

@Service
public class PurchaseSectorService {

    private final Logger logger = LoggerFactory.getLogger(PurchaseSectorService.class);

    private final AuthService authService;
    private final PurchaseSectorClient purchaseSectorClient;

    public PurchaseSectorService(AuthService authService, PurchaseSectorClient purchaseSectorClient) {
        this.authService = authService;
        this.purchaseSectorClient = purchaseSectorClient;
    }

    public boolean sendPurchaseRequest(CsvStockItem item, Integer purchaseQuantity) {

        // 1. autenticação na api para recuperar o token
        var token = authService.getToken();

        //  2. enviar solicitação de compra com o token gerado na chamada anterior
        var request = new PurchaseRequest(
                item.getItemId(),
                item.getItemName(),
                item.getSupplierName(),
                item.getSupplierEmail(),
                purchaseQuantity

        );

        var response = purchaseSectorClient.sendPurchaseResponse(token, request);

        // 3. validar se a resposta veio com sucesso
        if (response.getStatusCode().value() != HttpStatus.ACCEPTED.value()) {
            logger.error("Error while sending purchase request, status: {}, response {}",
                    response.getStatusCode(), response.getBody());
            return false;
        }

        return true;
    }
}
