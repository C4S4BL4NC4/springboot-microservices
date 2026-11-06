package com.java.billingservice.grpc;

import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(
            BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
                                     StreamObserver<billing.BillingResponse> responseObserver) {

        log.info("createBillingAccount request received {}", billingRequest.toString());

        // Business logic - e.g. save to database, perform calculates etc

        billing.BillingResponse response = billing.BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("success - grpc requests amongst services")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
