# ItemsApi

All URIs are relative to *http://localhost:8080/rest*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getItem**](ItemsApi.md#getItem) | **GET** /registry/items/{componentId} | Get the description of a profile or component |
| [**getItemSpec**](ItemsApi.md#getItemSpec) | **GET** /registry/items/{componentId}/spec | Get the specification for the profile or component |
| [**getItems**](ItemsApi.md#getItems) | **GET** /registry/items | Get a filtered list of descriptions of profiles and/or components |



## getItem

> BaseDescription getItem(componentId)

Get the description of a profile or component

### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ItemsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        ItemsApi apiInstance = new ItemsApi(defaultClient);
        String componentId = "componentId_example"; // String | 
        try {
            BaseDescription result = apiInstance.getItem(componentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ItemsApi#getItem");
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

[**BaseDescription**](BaseDescription.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/xml, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A description of the identified item |  -  |
| **404** | Item not found |  -  |


## getItemSpec

> ComponentSpec getItemSpec(componentId, accept)

Get the specification for the profile or component

### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ItemsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        ItemsApi apiInstance = new ItemsApi(defaultClient);
        String componentId = "componentId_example"; // String | 
        String accept = "accept_example"; // String | 
        try {
            ComponentSpec result = apiInstance.getItemSpec(componentId, accept);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ItemsApi#getItemSpec");
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
| **accept** | **String**|  | [optional] |

### Return type

[**ComponentSpec**](ComponentSpec.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/xml, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The component specification of the identified item. The JSON representation is derived from the primary specification which is stored as XML.  |  -  |
| **404** | Item not found |  -  |


## getItems

> List&lt;BaseDescription&gt; getItems(type, registrySpace, groupId, status, sortBy, sortDirection)

Get a filtered list of descriptions of profiles and/or components

### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ItemsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        ItemsApi apiInstance = new ItemsApi(defaultClient);
        String type = "COMPONENT"; // String | 
        String registrySpace = "PUBLIC"; // String | 
        Long groupId = 56L; // Long | 
        List<String> status = Arrays.asList(); // List<String> | 
        String sortBy = "name"; // String | 
        String sortDirection = "ASC"; // String | 
        try {
            List<BaseDescription> result = apiInstance.getItems(type, registrySpace, groupId, status, sortBy, sortDirection);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ItemsApi#getItems");
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
| **type** | **String**|  | [optional] [enum: COMPONENT, PROFILE] |
| **registrySpace** | **String**|  | [optional] [enum: PUBLIC, PRIVATE, GROUP] |
| **groupId** | **Long**|  | [optional] |
| **status** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: development, production, deprecated] |
| **sortBy** | **String**|  | [optional] [default to name] |
| **sortDirection** | **String**|  | [optional] [default to ASC] [enum: ASC, DESC] |

### Return type

[**List&lt;BaseDescription&gt;**](BaseDescription.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/xml, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of items that meet the filter criteria (if applicable) |  -  |

