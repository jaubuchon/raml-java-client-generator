
package from-example.resource.users;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import from-example.exceptions.ClientAPIException;
import from-example.resource.users.model.UsersGETResponse;

public class Users {

    private String _baseUrl;
    private Client _client;

    public Users() {
        _baseUrl = null;
        _client = null;
    }

    public Users(String baseUrl, Client _client) {
        _baseUrl = (baseUrl +"/users");
        this._client = _client;
    }

    protected Client getClient() {
        return this._client;
    }

    private String getBaseUri() {
        return _baseUrl;
    }

    /**
     * Returns the list of all users
     * 
     */
    public List<UsersGETResponse> get() {
        WebTarget target = this._client.target(getBaseUri());
        final javax.ws.rs.client.Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.get();
        if (response.getStatusInfo().getFamily()!= Family.SUCCESSFUL) {
            Response.StatusType statusInfo = response.getStatusInfo();
            throw new ClientAPIException(statusInfo.getStatusCode(), statusInfo.getReasonPhrase(), response.getStringHeaders(), response);
        }
        return response.readEntity((
new javax.ws.rs.core.GenericType<java.util.List<from-example.resource.users.model.UsersGETResponse>>() {}));
    }

}
