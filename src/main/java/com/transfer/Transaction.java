package com.transfer;

import java.io.IOException;
import java.io.Serializable;

public interface Transaction extends Serializable {
    public void execute() throws IOException;
}
