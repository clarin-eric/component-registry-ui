package eu.clarin.cmdi.componentregistry.openapi.client.api;

import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;

import eu.clarin.cmdi.componentregistry.openapi.client.model.RegistryUser;
import eu.clarin.cmdi.componentregistry.openapi.client.model.UserGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient.ResponseSpec;
import org.springframework.web.client.RestClientResponseException;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-11-17T19:49:24.253262+02:00[Europe/Riga]", comments = "Generator version: 7.26.0")
public class UserApi {
    private ApiClient apiClient;

    public UserApi() {
        this(new ApiClient());
    }

    public UserApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Info for a user, normally the current user
     * 
     * <p><b>200</b> - User info
     * @param userId The userId parameter
     * @param principalName The principalName parameter
     * @return RegistryUser
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getInfoRequestCreation(@jakarta.annotation.Nullable Long userId, @jakarta.annotation.Nullable String principalName) throws RestClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<>();

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "principalName", principalName));

        final String[] localVarAccepts = { 
            "application/json", "application/xml"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<RegistryUser> localVarReturnType = new ParameterizedTypeReference<>() {};
        return apiClient.invokeAPI("/user", HttpMethod.GET, pathParams, localVarQueryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Info for a user, normally the current user
     * 
     * <p><b>200</b> - User info
     * @param userId The userId parameter
     * @param principalName The principalName parameter
     * @return RegistryUser
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public RegistryUser getInfo(@jakarta.annotation.Nullable Long userId, @jakarta.annotation.Nullable String principalName) throws RestClientResponseException {
        ParameterizedTypeReference<RegistryUser> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getInfoRequestCreation(userId, principalName).body(localVarReturnType);
    }

    /**
     * Info for a user, normally the current user
     * 
     * <p><b>200</b> - User info
     * @param userId The userId parameter
     * @param principalName The principalName parameter
     * @return ResponseEntity&lt;RegistryUser&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RegistryUser> getInfoWithHttpInfo(@jakarta.annotation.Nullable Long userId, @jakarta.annotation.Nullable String principalName) throws RestClientResponseException {
        ParameterizedTypeReference<RegistryUser> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getInfoRequestCreation(userId, principalName).toEntity(localVarReturnType);
    }

    /**
     * Info for a user, normally the current user
     * 
     * <p><b>200</b> - User info
     * @param userId The userId parameter
     * @param principalName The principalName parameter
     * @return ResponseSpec
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getInfoWithResponseSpec(@jakarta.annotation.Nullable Long userId, @jakarta.annotation.Nullable String principalName) throws RestClientResponseException {
        return getInfoRequestCreation(userId, principalName);
    }

    /**
     * Get a user&#39;s teams
     * 
     * <p><b>200</b> - A list of teams of which the user is a member
     * @param userId The userId parameter
     * @return List&lt;UserGroup&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTeamsRequestCreation(@jakarta.annotation.Nullable Long userId) throws RestClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<>();

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));

        final String[] localVarAccepts = { 
            "application/json", "application/xml"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<List<UserGroup>> localVarReturnType = new ParameterizedTypeReference<>() {};
        return apiClient.invokeAPI("/user/teams", HttpMethod.GET, pathParams, localVarQueryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get a user&#39;s teams
     * 
     * <p><b>200</b> - A list of teams of which the user is a member
     * @param userId The userId parameter
     * @return List&lt;UserGroup&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public List<UserGroup> getTeams(@jakarta.annotation.Nullable Long userId) throws RestClientResponseException {
        ParameterizedTypeReference<List<UserGroup>> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getTeamsRequestCreation(userId).body(localVarReturnType);
    }

    /**
     * Get a user&#39;s teams
     * 
     * <p><b>200</b> - A list of teams of which the user is a member
     * @param userId The userId parameter
     * @return ResponseEntity&lt;List&lt;UserGroup&gt;&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserGroup>> getTeamsWithHttpInfo(@jakarta.annotation.Nullable Long userId) throws RestClientResponseException {
        ParameterizedTypeReference<List<UserGroup>> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getTeamsRequestCreation(userId).toEntity(localVarReturnType);
    }

    /**
     * Get a user&#39;s teams
     * 
     * <p><b>200</b> - A list of teams of which the user is a member
     * @param userId The userId parameter
     * @return ResponseSpec
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTeamsWithResponseSpec(@jakarta.annotation.Nullable Long userId) throws RestClientResponseException {
        return getTeamsRequestCreation(userId);
    }
}
