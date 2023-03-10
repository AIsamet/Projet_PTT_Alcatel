package net.eicnam.fip1.sdk.rainbow.bubbles;

import net.eicnam.fip1.ptt.backend.utils.DotEnvReader;
import net.eicnam.fip1.ptt.backend.utils.JWTDecode;
import net.eicnam.fip1.sdk.rainbow.RainbowApplication;
import net.eicnam.fip1.sdk.rainbow.utils.Endpoint;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpHeader;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpMethod;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpParams;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpRequest;

public class Bubbles {

    private static final RainbowApplication rainbow = RainbowApplication.getInstance();

    private final RainbowApplication rainbowApplication = RainbowApplication.getInstance();
    private final DotEnvReader envReader = rainbowApplication.getEnvReader();

    private final String baseUrl = envReader.getValue("API_URL");

    private final HttpHeader header;
    private final String userId;

    public Bubbles() {
        final String token = rainbow.getToken();

        this.userId = JWTDecode.getId(token);
        this.header = new HttpHeader().addHeader("Authorization", String.format("Bearer %s", token));
    }

    public String getAll() {
        final HttpParams params = new HttpParams()
                .addParam("limit", "1000")
                .addParam("userId", this.userId)
                .addParam("format", "full");

        final HttpRequest request = new HttpRequest(baseUrl + Endpoint.ENDUSER_ROOMS, HttpMethod.GET, header, params);

        return request.connect()
                .send()
                .disconnect()
                .getResponse();
    }
}
