package com.ktar5.utilities.annotation.dontoverride;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.SOURCE)
public @interface DontOverride {
}