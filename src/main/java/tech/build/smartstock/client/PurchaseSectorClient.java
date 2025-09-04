package tech.build.smartstock.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import tech.build.smartstock.client.dto.PurchaseRequest;
import tech.build.smartstock.client.dto.PurchaseResponse;

@FeignClient(name = "PurchaseSectorClient", url = "${api.purchase-sector-url}")
public interface PurchaseSectorClient {

    @PostMapping(path = "/api/purchases")
    ResponseEntity<PurchaseResponse> sendPurchaseResponse(@RequestHeader("Authorization") String token,
                                                          @RequestBody PurchaseRequest request);


}
