package com.dima.blogmobile;

import android.support.annotation.CallSuper;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseTest {

    @Before
    @CallSuper
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

}
