package com.zhangjun.cniaoplay.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/3/21.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
