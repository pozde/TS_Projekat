package ba.tim2.systemevents;

import ba.tim2.systemevents.grpc.APIResponse;
import ba.tim2.systemevents.grpc.LogRequest;
import ba.tim2.systemevents.grpc.eventGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@GRpcService
public class EventImpl extends eventGrpc.eventImplBase {
    //originalna implementacija interfejsa je da baci runtime exception

    ApplicationContext applicationContext;
    public EventImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public void log(LogRequest request, StreamObserver<APIResponse> responseObserver){
        String message = new StringBuilder()
                .append("Event time: ").append(request.getTimestamp()).append(";\n")
                .append("Resource: ").append(request.getResource()).append(";\n")
                .append("Action: ").append(request.getAction()).append(";\n")
                .append("Status: ").append(request.getStatus()).append(";\n").toString();

        actionRepository = applicationContext.getBean(ActionRepository.class);
        Action action = new Action(Long.valueOf(1), request.getAction(), request.getStatus(), request.getResource(), request.getTimestamp());
        actionRepository.save(action);
        System.out.println(message);

        APIResponse response = APIResponse.newBuilder()
                .setMessage("Test message log")
                .setResponseCode(200)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("Request sent eventImpl");
    }
}
