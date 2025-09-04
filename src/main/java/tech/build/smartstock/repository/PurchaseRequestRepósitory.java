package tech.build.smartstock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.build.smartstock.entity.PurchaseRequestEntity;

public interface PurchaseRequestRepósitory extends MongoRepository<PurchaseRequestEntity, String> {
}
