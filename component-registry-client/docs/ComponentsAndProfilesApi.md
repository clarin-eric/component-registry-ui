# ComponentsAndProfilesApi

All URIs are relative to *http://localhost:8080/rest*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getComponentItem**](ComponentsAndProfilesApi.md#getComponentItem) | **GET** /registry/components/{componentId}/description |  |
| [**getComponents**](ComponentsAndProfilesApi.md#getComponents) | **GET** /registry/components |  |
| [**getProfileItem**](ComponentsAndProfilesApi.md#getProfileItem) | **GET** /registry/profiles/{componentId}/description |  |
| [**getProfiles**](ComponentsAndProfilesApi.md#getProfiles) | **GET** /registry/profiles |  |



## getComponentItem

> ComponentDescription getComponentItem(componentId)



### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ComponentsAndProfilesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        ComponentsAndProfilesApi apiInstance = new ComponentsAndProfilesApi(defaultClient);
        String componentId = "componentId_example"; // String | 
        try {
            ComponentDescription result = apiInstance.getComponentItem(componentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ComponentsAndProfilesApi#getComponentItem");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **componentId** | **String**|  | |

### Return type

[**ComponentDescription**](ComponentDescription.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json, application/xml


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## getComponents

> ComponentsList getComponents(registrySpace, status, sortBy, sortDirection, groupId)



### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ComponentsAndProfilesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        ComponentsAndProfilesApi apiInstance = new ComponentsAndProfilesApi(defaultClient);
        String registrySpace = "PUBLIC"; // String | 
        List<String> status = Arrays.asList(); // List<String> | 
        String sortBy = "name"; // String | 
        String sortDirection = "ASC"; // String | 
        Long groupId = 56L; // Long | 
        try {
            ComponentsList result = apiInstance.getComponents(registrySpace, status, sortBy, sortDirection, groupId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ComponentsAndProfilesApi#getComponents");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **registrySpace** | **String**|  | [optional] [default to PUBLIC] [enum: PUBLIC, PRIVATE, GROUP] |
| **status** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: development, production, deprecated] |
| **sortBy** | **String**|  | [optional] [default to name] |
| **sortDirection** | **String**|  | [optional] [default to ASC] [enum: ASC, DESC] |
| **groupId** | **Long**|  | [optional] |

### Return type

[**ComponentsList**](ComponentsList.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json, application/xml


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## getProfileItem

> ProfileDescription getProfileItem(componentId)



### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ComponentsAndProfilesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        ComponentsAndProfilesApi apiInstance = new ComponentsAndProfilesApi(defaultClient);
        String componentId = "componentId_example"; // String | 
        try {
            ProfileDescription result = apiInstance.getProfileItem(componentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ComponentsAndProfilesApi#getProfileItem");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **componentId** | **String**|  | |

### Return type

[**ProfileDescription**](ProfileDescription.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json, application/xml


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## getProfiles

> ProfilesList getProfiles(registrySpace, status, sortBy, sortDirection, groupId)



### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ComponentsAndProfilesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        ComponentsAndProfilesApi apiInstance = new ComponentsAndProfilesApi(defaultClient);
        String registrySpace = "PUBLIC"; // String | 
        List<String> status = Arrays.asList(); // List<String> | 
        String sortBy = "name"; // String | 
        String sortDirection = "ASC"; // String | 
        Long groupId = 56L; // Long | 
        try {
            ProfilesList result = apiInstance.getProfiles(registrySpace, status, sortBy, sortDirection, groupId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ComponentsAndProfilesApi#getProfiles");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **registrySpace** | **String**|  | [optional] [enum: PUBLIC, PRIVATE, GROUP] |
| **status** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: development, production, deprecated] |
| **sortBy** | **String**|  | [optional] [default to name] |
| **sortDirection** | **String**|  | [optional] [default to ASC] [enum: ASC, DESC] |
| **groupId** | **Long**|  | [optional] |

### Return type

[**ProfilesList**](ProfilesList.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json, application/xml


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

