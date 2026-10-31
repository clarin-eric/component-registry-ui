# UserApi

All URIs are relative to *http://localhost:8080/rest*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getInfo**](UserApi.md#getInfo) | **GET** /user | Info for a user, normally the current user |
| [**getTeams**](UserApi.md#getTeams) | **GET** /user/teams | Get a user&#39;s teams |



## getInfo

> RegistryUser getInfo(userId, principalName)

Info for a user, normally the current user

### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        UserApi apiInstance = new UserApi(defaultClient);
        Long userId = 56L; // Long | 
        String principalName = "principalName_example"; // String | 
        try {
            RegistryUser result = apiInstance.getInfo(userId, principalName);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#getInfo");
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
| **userId** | **Long**|  | [optional] |
| **principalName** | **String**|  | [optional] |

### Return type

[**RegistryUser**](RegistryUser.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json, application/xml


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | User info |  -  |


## getTeams

> List&lt;UserGroup&gt; getTeams(userId)

Get a user&#39;s teams

### Example

```java
// Import classes:
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiException;
import eu.clarin.cmdi.componentregistry.openapi.client.Configuration;
import eu.clarin.cmdi.componentregistry.openapi.client.models.*;
import eu.clarin.cmdi.componentregistry.openapi.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/rest");

        UserApi apiInstance = new UserApi(defaultClient);
        Long userId = 56L; // Long | 
        try {
            List<UserGroup> result = apiInstance.getTeams(userId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#getTeams");
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
| **userId** | **Long**|  | [optional] |

### Return type

[**List&lt;UserGroup&gt;**](UserGroup.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json, application/xml


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of teams of which the user is a member |  -  |

