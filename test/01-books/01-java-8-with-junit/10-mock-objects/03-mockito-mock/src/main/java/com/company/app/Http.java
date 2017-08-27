package com.company.app;

import java.io.IOException;

public interface Http {
  String get(String url) throws IOException;
}
