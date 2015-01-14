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

    if (req.method == "BAN" || req.method == "PURGE") {
        # Same ACL check as above:
        #if (!client.ip ~ purge) {
            #return(synth(403, "Not allowed."));
        #}

        #ban("obj.http.x-url ~ " + req.url); # Assumes req.url is a regex. This might be a bit too simple

        ban ("obj.http.x-article-ids ~ " + req.url);

        # Throw a synthetic page so the
        # request won't go to the backend.
        return(synth(200, "Ban added"));
    }

    if (!req.url ~ "\.(jpg|jpeg|png|gif|ico|tiff|tif|bmp|ppm|pgm|xcf|psd|webp|svg)") {
        set req.http.X-Esi = "1";
        set req.http.Surrogate-Capability = "ESI/1.0";
    }
}

sub vcl_backend_response {
    # Happens after we have read the response headers from the backend.
    #
    # Here you clean the response headers, removing silly Set-Cookie headers
    # and other mistakes your backend does.

    #if (beresp.http.Content-Type ~ "^image/") {
    if (beresp.status == 404) {
        #set beresp.http.Cache-Control = "public, max-age=60";
        #set beresp.ttl = 60s;
        set beresp.http.Cache-Control = "public, max-age=0";
        set beresp.ttl = 0s;
    }

    if (beresp.http.X-Esi) {
        set beresp.do_esi = true;
        unset beresp.http.X-Esi; # remove header
    }

    set beresp.http.X-url = bereq.url;
}

sub vcl_deliver {
    # Happens when we have all the pieces we need, and are about to send the
    # response to the client.
    #
    # You can do accounting or modifying the final object here.

    unset resp.http.Server;
    unset resp.http.X-Powered-By;
}