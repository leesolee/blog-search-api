package com.sojung.blog.constant;

public class Constants {
    public static final String KAKAO_BASE_URL = "http://dapi.kakao.com";
    // error test
//    public static final String KAKAO_BLOG_API_URL = "/v2/search/error";
    public static final String KAKAO_BLOG_API_URL = "/v2/search/blog";
    public static final String KAKAO_APP_KEY = "a01652d0074d02f8c882c481f65ea511";
    public static final String KAKAO_HEADER_KEY = "Authorization";

    public static final String NAVER_BASE_URL = "https://openapi.naver.com";
    public static final String NAVER_BOOK_API_URL = "/v1/search/blog.json";
    public static final String NAVER_CLIENT_ID = "nVnMe4dvRnWBNKvqNRz4";
    public static final String NAVER_CLIENT_SECRET = "_xOK2XWBmc";
    public static final String NAVER_CLIENT_ID_KEY = "X-Naver-Client-Id";
    public static final String NAVER_CLIENT_SECRET_KEY = "X-Naver-Client-Secret";

    // error codes
    public static final String KAKAO_NOT_FOUND_ERROR = "kakao api 통신 에러";

}
