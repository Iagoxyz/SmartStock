package tech.build.smartstock.service;

import org.springframework.stereotype.Service;
import tech.build.smartstock.domain.CsvStockItem;

import java.io.IOException;

@Service
public class SmartStockService {

    private final PurchaseSectorService purchaseSectorService;
    private final ReportService reportService;

    public SmartStockService(PurchaseSectorService purchaseSectorService, ReportService reportService) {
        this.purchaseSectorService = purchaseSectorService;
        this.reportService = reportService;
    }

    public void start(String reportPath) {

        // 1. ler o arquivo csv
        try {
            var items = reportService.readStockReport(reportPath);

            items.forEach(item -> {
                if (item.getQuantity() < item.getReorderThreshold()) {

                    // 1. calcular a quantidade a ser comprada
                    var reorderQuantity = calculateReorderQuantity(item);

                    // 2. para cada item do csv chamar a api do setor de compras
                    purchaseSectorService.sendPurchaseRequest(item, reorderQuantity);


                    // 3. salvar no mongoDB os items que foram comprados
                }
            });


        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    private Integer calculateReorderQuantity(CsvStockItem items) {
        return items.getReorderThreshold() + ((int)Math.ceil(items.getReorderThreshold() * 0.2));
    }


}
