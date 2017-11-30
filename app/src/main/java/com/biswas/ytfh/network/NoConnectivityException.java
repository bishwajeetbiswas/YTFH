package com.biswas.ytfh.network;

import java.io.IOException;

/**
 * Created by arun on 28/09/17.
 */

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}