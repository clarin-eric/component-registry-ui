package eu.clarin.cmdi.componentregistry.openapi.client.api;

import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;

import eu.clarin.cmdi.componentregistry.openapi.client.model.BaseDescription;
import eu.clarin.cmdi.componentregistry.openapi.client.model.ComponentSpec;

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

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-03-12T17:22:45.395484+01:00[Europe/Warsaw]", comments = "Generator version: 7.14.0")
public class ItemsApi {
    private ApiClient apiClient;

    public ItemsApi() {
        this(new ApiClient());
    }

    public ItemsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get the description of a profile or component
     * 
     * <p><b>200</b> - A description of the identified item
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @return BaseDescription
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getItemRequestCreation(@jakarta.annotation.Nonnull String componentId) throws RestClientResponseException {
        Object postBody = null;
        // verify the required parameter 'componentId' is set
        if (componentId == null) {
            throw new RestClientResponseException("Missing the required parameter 'componentId' when calling getItem", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<>();

        pathParams.put("componentId", componentId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<>();

        final String[] localVarAccepts = { 
            "application/xml", "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<BaseDescription> localVarReturnType = new ParameterizedTypeReference<>() {};
        return apiClient.invokeAPI("/registry/items/{componentId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get the description of a profile or component
     * 
     * <p><b>200</b> - A description of the identified item
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @return BaseDescription
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public BaseDescription getItem(@jakarta.annotation.Nonnull String componentId) throws RestClientResponseException {
        ParameterizedTypeReference<BaseDescription> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getItemRequestCreation(componentId).body(localVarReturnType);
    }

    /**
     * Get the description of a profile or component
     * 
     * <p><b>200</b> - A description of the identified item
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @return ResponseEntity&lt;BaseDescription&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BaseDescription> getItemWithHttpInfo(@jakarta.annotation.Nonnull String componentId) throws RestClientResponseException {
        ParameterizedTypeReference<BaseDescription> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getItemRequestCreation(componentId).toEntity(localVarReturnType);
    }

    /**
     * Get the description of a profile or component
     * 
     * <p><b>200</b> - A description of the identified item
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @return ResponseSpec
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getItemWithResponseSpec(@jakarta.annotation.Nonnull String componentId) throws RestClientResponseException {
        return getItemRequestCreation(componentId);
    }
    /**
     * Get the specification for the profile or component
     * 
     * <p><b>200</b> - The component specification of the identified item. The JSON representation is derived from the primary specification which is stored as XML. 
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @param accept The accept parameter
     * @return ComponentSpec
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getItemSpecRequestCreation(@jakarta.annotation.Nonnull String componentId, @jakarta.annotation.Nullable String accept) throws RestClientResponseException {
        Object postBody = null;
        // verify the required parameter 'componentId' is set
        if (componentId == null) {
            throw new RestClientResponseException("Missing the required parameter 'componentId' when calling getItemSpec", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<>();

        pathParams.put("componentId", componentId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<>();


        if (accept != null)
        headerParams.add("Accept", apiClient.parameterToString(accept));
        final String[] localVarAccepts = { 
            "application/xml", "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ComponentSpec> localVarReturnType = new ParameterizedTypeReference<>() {};
        return apiClient.invokeAPI("/registry/items/{componentId}/spec", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get the specification for the profile or component
     * 
     * <p><b>200</b> - The component specification of the identified item. The JSON representation is derived from the primary specification which is stored as XML. 
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @param accept The accept parameter
     * @return ComponentSpec
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ComponentSpec getItemSpec(@jakarta.annotation.Nonnull String componentId, @jakarta.annotation.Nullable String accept) throws RestClientResponseException {
        ParameterizedTypeReference<ComponentSpec> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getItemSpecRequestCreation(componentId, accept).body(localVarReturnType);
    }

    /**
     * Get the specification for the profile or component
     * 
     * <p><b>200</b> - The component specification of the identified item. The JSON representation is derived from the primary specification which is stored as XML. 
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @param accept The accept parameter
     * @return ResponseEntity&lt;ComponentSpec&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ComponentSpec> getItemSpecWithHttpInfo(@jakarta.annotation.Nonnull String componentId, @jakarta.annotation.Nullable String accept) throws RestClientResponseException {
        ParameterizedTypeReference<ComponentSpec> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getItemSpecRequestCreation(componentId, accept).toEntity(localVarReturnType);
    }

    /**
     * Get the specification for the profile or component
     * 
     * <p><b>200</b> - The component specification of the identified item. The JSON representation is derived from the primary specification which is stored as XML. 
     * <p><b>404</b> - Item not found
     * @param componentId The componentId parameter
     * @param accept The accept parameter
     * @return ResponseSpec
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getItemSpecWithResponseSpec(@jakarta.annotation.Nonnull String componentId, @jakarta.annotation.Nullable String accept) throws RestClientResponseException {
        return getItemSpecRequestCreation(componentId, accept);
    }
    /**
     * Get a filtered list of descriptions of profiles and/or components
     * 
     * <p><b>200</b> - A list of items that meet the filter criteria (if applicable)
     * @param type The type parameter
     * @param status The status parameter
     * @param sortBy The sortBy parameter
     * @param sortDirection The sortDirection parameter
     * @return List&lt;BaseDescription&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getItemsRequestCreation(@jakarta.annotation.Nullable String type, @jakarta.annotation.Nullable List<String> status, @jakarta.annotation.Nullable String sortBy, @jakarta.annotation.Nullable String sortDirection) throws RestClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "status", status));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortDirection", sortDirection));
        
        final String[] localVarAccepts = { 
            "application/xml", "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<List<BaseDescription>> localVarReturnType = new ParameterizedTypeReference<>() {};
        return apiClient.invokeAPI("/registry/items", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get a filtered list of descriptions of profiles and/or components
     * 
     * <p><b>200</b> - A list of items that meet the filter criteria (if applicable)
     * @param type The type parameter
     * @param status The status parameter
     * @param sortBy The sortBy parameter
     * @param sortDirection The sortDirection parameter
     * @return List&lt;BaseDescription&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public List<BaseDescription> getItems(@jakarta.annotation.Nullable String type, @jakarta.annotation.Nullable List<String> status, @jakarta.annotation.Nullable String sortBy, @jakarta.annotation.Nullable String sortDirection) throws RestClientResponseException {
        ParameterizedTypeReference<List<BaseDescription>> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getItemsRequestCreation(type, status, sortBy, sortDirection).body(localVarReturnType);
    }

    /**
     * Get a filtered list of descriptions of profiles and/or components
     * 
     * <p><b>200</b> - A list of items that meet the filter criteria (if applicable)
     * @param type The type parameter
     * @param status The status parameter
     * @param sortBy The sortBy parameter
     * @param sortDirection The sortDirection parameter
     * @return ResponseEntity&lt;List&lt;BaseDescription&gt;&gt;
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<BaseDescription>> getItemsWithHttpInfo(@jakarta.annotation.Nullable String type, @jakarta.annotation.Nullable List<String> status, @jakarta.annotation.Nullable String sortBy, @jakarta.annotation.Nullable String sortDirection) throws RestClientResponseException {
        ParameterizedTypeReference<List<BaseDescription>> localVarReturnType = new ParameterizedTypeReference<>() {};
        return getItemsRequestCreation(type, status, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * Get a filtered list of descriptions of profiles and/or components
     * 
     * <p><b>200</b> - A list of items that meet the filter criteria (if applicable)
     * @param type The type parameter
     * @param status The status parameter
     * @param sortBy The sortBy parameter
     * @param sortDirection The sortDirection parameter
     * @return ResponseSpec
     * @throws RestClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getItemsWithResponseSpec(@jakarta.annotation.Nullable String type, @jakarta.annotation.Nullable List<String> status, @jakarta.annotation.Nullable String sortBy, @jakarta.annotation.Nullable String sortDirection) throws RestClientResponseException {
        return getItemsRequestCreation(type, status, sortBy, sortDirection);
    }
}
