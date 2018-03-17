package com.ktar5.utilities.common.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.SOURCE)
public @interface DontOverride { }