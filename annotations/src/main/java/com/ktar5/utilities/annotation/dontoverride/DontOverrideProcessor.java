/*
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ktar5.utilities.annotation.dontoverride;

import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import java.util.Set;

@SupportedAnnotationTypes("java.lang.Override") //processor is triggered by Override annotation
public class DontOverrideProcessor extends AbstractProcessor {
    private Trees trees;

    @Override
    public void init(ProcessingEnvironment pe) {
        super.init(pe);
        trees = Trees.instance(pe);
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(Override.class)) {
            if (e.getAnnotation(OverrideDontOverride.class) != null) return false;
            System.out.println("\nProcessing method: " + e.getSimpleName());
            DontOverrideCodeAnalyzerTreeScanner codeScanner = new DontOverrideCodeAnalyzerTreeScanner();
            codeScanner.setMethodName(e.getSimpleName().toString());

            TreePath tp = trees.getPath(e.getEnclosingElement());
            codeScanner.scan(tp, trees);

            if (codeScanner.isDontOverrideUser()) {
                processingEnv.getMessager().printMessage(
                        Kind.ERROR,
                        "Overriding method '" + codeScanner.getMethodName() + "' should not override because of @DontOverride !",
                        e);
            }
        }

        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}
