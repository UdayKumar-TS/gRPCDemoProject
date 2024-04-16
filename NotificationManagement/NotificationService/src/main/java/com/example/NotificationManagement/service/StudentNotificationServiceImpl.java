package com.example.NotificationManagement.service;

import com.example.NotificationManagement.exception.NotificationNotFoundException;
import com.example.NotificationManagement.model.Address;
import com.example.NotificationManagement.model.Notification;
import com.example.NotificationManagement.model.Student;
import com.proto.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

import java.util.List;

@GrpcService
public class StudentNotificationServiceImpl extends StudentNotificationServiceGrpc.StudentNotificationServiceImplBase
{
    Logger log = LoggerFactory.getLogger(StudentNotificationServiceImpl.class);
    @Autowired
    NotificationService notificationService;
    @Override
    public void getNotificationById(NotificationRequest request, StreamObserver<NotificationResponse> responseObserver) {

        int notificationId = request.getNotificationId();
        Notification notification = notificationService.getNotificationById(notificationId);
        if(notification!=null)
        {
            log.info(notification.getNotificationId()+"");
            NotificationResponse response = NotificationResponse.newBuilder().setNotificationId(notification.getNotificationId())
                    .setStudentName(notification.getPayload().getStudentName()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        else
        {
            log.info("Notification doesn't exist");
            responseObserver.onError(new NotificationNotFoundException("Notification with id "+notificationId+" not found"));

        }

    }

    @Override
    public void getAllNotification(PageRequest request, StreamObserver<NotificationsResponseList> responseObserver) {
        List<Notification> notifications= notificationService.getNotificationsWithPagination(request.getPageNum(), request.getPageSize());
        NotificationsResponseList.Builder responseBuilder = NotificationsResponseList.newBuilder();
        for(Notification notification : notifications) {
            NotificationsResponse notificationResponse = mapToNotificationsResponse(notification);
            responseBuilder.addNotifications(notificationResponse);
        }
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllNotificationsWithSorting(SortRequest request, StreamObserver<NotificationsResponseList> responseObserver) {

        List<Notification> notifications = notificationService.getNotificationsWithSorting(request.getPageNum(), request.getPageSize(), request.getSortField(),request.getDirection());
        NotificationsResponseList.Builder notificationsResponseList = NotificationsResponseList.newBuilder();
        for(Notification notification : notifications)
        {
            NotificationsResponse notificationsResponse = mapToNotificationsResponse(notification);
            notificationsResponseList.addNotifications(notificationsResponse);
        }
        responseObserver.onNext(notificationsResponseList.build());
        responseObserver.onCompleted();
    }


    private NotificationsResponse mapToNotificationsResponse(Notification notification) {
        Student student = notification.getPayload();
        List<Address> addresses = student.getAddress();
        AddressResponseList.Builder addressBuilder = AddressResponseList.newBuilder();
        for( Address address : addresses)
        {
            AddressResponse addressResponse = AddressResponse.newBuilder().setCity(address.getCity()).setPincode(address.getPincode()).build();
            addressBuilder.addAddress(addressResponse);
        }

        StudentResponse studentResponse = StudentResponse.newBuilder().setStudentName(student.getStudentName())
                .setRollNum(student.getRollNum())
                .setAge(student.getAge())
                .setAddresses(addressBuilder.build()).build();

        return NotificationsResponse.newBuilder()
                .setNotificationId(notification.getNotificationId())
                .setNotificationType(notification.getNotificationType())
                .setPayload(studentResponse)
                .setCreatedOn(notification.getCreatedOn().toString()).build();
    }

}
