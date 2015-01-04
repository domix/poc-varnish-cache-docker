vcl 4.0;

backend default {
    .host = "web";
    .port = "9191";
}

backend management {
    .host = "web";
    .port = "9192";
}

sub vcl_recv {
    # Happens before we check if we have this in cache already.
    #
    # Typically you clean up the request here, removing cookies you don't need,
    # rewriting the request, etc.

    if (req.url ~ "^/management/") {
        set req.backend_hint = management;
    } else {
        set req.backend_hint = default;
    }
}

    sub vcl_backend_response {
        # Happens after we have read the response headers from the backend.
        #
        # Here you clean the response headers, removing silly Set-Cookie headers
        # and other mistakes your backend does.
    }

    sub vcl_deliver {
        # Happens when we have all the pieces we need, and are about to send the
        # response to the client.
        #
        # You can do accounting or modifying the final object here.
    }