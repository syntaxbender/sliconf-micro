package javaday.istanbul.sliconf.micro.controller.event;

import io.swagger.annotations.*;
import javaday.istanbul.sliconf.micro.model.event.Event;
import javaday.istanbul.sliconf.micro.model.response.ResponseMessage;
import javaday.istanbul.sliconf.micro.provider.EventControllerMessageProvider;
import javaday.istanbul.sliconf.micro.service.event.EventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Api
@Path("/service/events/list/:userId")
@Produces("application/json")
@Component
public class ListEventsRoute implements Route {

    private EventControllerMessageProvider messageProvider;
    private EventRepositoryService repositoryService;

    @Autowired
    public ListEventsRoute(EventControllerMessageProvider messageProvider,
                           EventRepositoryService eventRepositoryService) {
        this.messageProvider = messageProvider;
        this.repositoryService = eventRepositoryService;
    }

    @GET
    @ApiOperation(value = "Returns event list of user", nickname = "ListEventsRoute")
    @ApiImplicitParams({ //
            @ApiImplicitParam(required = true, dataType = "string", name = "token", paramType = "header"), //
            @ApiImplicitParam(required = true, dataType = "string", name = ":userId", paramType = "path")
    })
    @ApiResponses(value = { //
            @ApiResponse(code = 200, message = "Success", response = ResponseMessage.class), //
            @ApiResponse(code = 400, message = "Invalid input data", response = ResponseMessage.class), //
            @ApiResponse(code = 401, message = "Unauthorized", response = ResponseMessage.class), //
            @ApiResponse(code = 404, message = "User not found", response = ResponseMessage.class) //
    })
    @Override
    public ResponseMessage handle(@ApiParam(hidden = true) Request request, @ApiParam(hidden = true) Response response) throws Exception {
        ResponseMessage responseMessage;

        String userId = request.params("userId");

        // event var mı diye kontrol et
        Map<String, List<Event>> events = repositoryService.findByExecutiveUser(userId);

        if (Objects.isNull(events)) {
            responseMessage = new ResponseMessage(false,
                    messageProvider.getMessage("eventCanNotFound"), new Object());
            return responseMessage;
        }

        responseMessage = new ResponseMessage(true,
                messageProvider.getMessage("eventListedSuccessfully"), events);
        return responseMessage;
    }
}
