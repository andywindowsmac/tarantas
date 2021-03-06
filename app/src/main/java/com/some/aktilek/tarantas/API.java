package com.some.aktilek.tarantas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by adilkhankenzhetaev on 5/21/18.
 */

public class API {
    private static String API_URL = "http://192.168.0.104:8888/koleso/API";

    public static void loginIndividual(Context context, String email, String password,Response.Listener<String> response,Response.ErrorListener error) {
             /* Endpoint */
            String endpoint = API_URL + "/api_login_phys_user.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            final Map<String, String> body = new HashMap<String, String>();
            body.put("email", email);
            body.put("password", password);

            StringRequest request = new StringRequest(Request.Method.POST, endpoint, response, error) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return body;
                }

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }
            };

            queue.add(request);
    }

    public static void loginEntity(Context context, String email, String password,Response.Listener response,Response.ErrorListener error) {
            /* Endpoint */
            String endpoint = API_URL + "/api_login_law_user.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            final Map<String, String> body = new HashMap<String, String>();
            body.put("email", email);
            body.put("password", password);

            StringRequest request = new StringRequest(Request.Method.POST, endpoint, response, error) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return body;
                }

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }
            };

            queue.add(request);
    }

    public static void signUpIndividual(Context context, String number, String email, String password, Response.Listener response,Response.ErrorListener error) {
            /* Endpoint */
            String endpoint = API_URL + "/api_regis_phys_user.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            final Map<String, String> body = new HashMap<String, String>();
            body.put("email", email);
            body.put("password", password);
            body.put("number", number);

            StringRequest request = new StringRequest(Request.Method.POST, endpoint, response, error) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return body;
                }

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }
            };

            queue.add(request);
    }

    public static void signUpEntity(Context context, String email, String password, String companyName, int locationId, String name, String surname, String number, String address, Response.Listener response,Response.ErrorListener error) {
            /* Endpoint */
            String endpoint = API_URL + "/api_regis_phys_user.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            final Map<String, String> body = new HashMap<String, String>();
            body.put("email", email);
            body.put("password", password);
            body.put("number", number);
            body.put("email", email);
            body.put("password", password);
            body.put("comp_name", companyName);
            body.put("location_id", 0 + "");
            body.put("name", name);
            body.put("surname", surname);
            body.put("number", number);
            body.put("address", address);

            StringRequest request = new StringRequest(Request.Method.POST, endpoint, response, error) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return body;
                }

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }
            };

            queue.add(request);
    }

    public static void addComment(Context context, int productId, int individualId, String text, Response.Listener response,Response.ErrorListener error) {
        try {
            /* Endpoint */
            String endpoint = API_URL + "/api_add_comment.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("ads_id", productId);
            jsonBody.put("phys_user_id", individualId);
            jsonBody.put("var", text);

            final String requestBody = jsonBody.toString();

            /* Request */
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, null, response, error){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void addOrders(Context context, int productId, int entityId, int individualId, int count, int price, Response.Listener response,Response.ErrorListener error) {
        try {
            /* Endpoint */
            String endpoint = API_URL + "/api_add_orders.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("ads_id", productId);
            jsonBody.put("law_user_id", entityId);
            jsonBody.put("phys_user_id", individualId);
            jsonBody.put("count", count);
            jsonBody.put("price", price);
            final String requestBody = jsonBody.toString();

            /* Request */
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, null, response, error){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void deleteProducts(Context context, int productId, Response.Listener response,Response.ErrorListener error) {
        try {
            /* Endpoint */
            String endpoint = API_URL + "/api_delete_ads.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", productId);
            final String requestBody = jsonBody.toString();

            /* Request */
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, null, response, error){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void deleteBasket(Context context, int basketId, Response.Listener response,Response.ErrorListener error) {
        try {
            /* Endpoint */
            String endpoint = API_URL + "/api_delete_basket.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", basketId);
            final String requestBody = jsonBody.toString();

            /* Request */
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, null, response, error){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void insertBasket(Context context, int individualId, int productId, int count, Response.Listener response,Response.ErrorListener error) {
        try {
            /* Endpoint */
            String endpoint = API_URL + "/api_insert_ads.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("phys_user_id", individualId);
            jsonBody.put("ads_id", productId);
            jsonBody.put("count", count);
            final String requestBody = jsonBody.toString();

            /* Request */
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, null, response, error){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeBasket(Context context, int individualId, int productId, Response.Listener response,Response.ErrorListener error) {
        try {
            /* Endpoint */
            String endpoint = API_URL + "/api_remove_basket.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("ads_id", individualId);
            jsonBody.put("phys_user_id", productId);
            final String requestBody = jsonBody.toString();

            /* Request */
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, null, response, error){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void getIndividualUser(Context context, int individualUserId, Response.Listener response,Response.ErrorListener error) {
        try {
            /* Endpoint */
            String endpoint = API_URL + "/api_remove_basket.php";
            RequestQueue queue = Volley.newRequestQueue(context);

            /* Body */
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("individualUserId", individualUserId);
            final String requestBody = jsonBody.toString();

            /* Request */
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, null, response, error){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
