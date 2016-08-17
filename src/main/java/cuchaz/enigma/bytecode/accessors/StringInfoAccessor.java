/*******************************************************************************
 * Copyright (c) 2015 Jeff Martin.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Contributors:
 * Jeff Martin - initial API and implementation
 ******************************************************************************/
package cuchaz.enigma.bytecode.accessors;

import java.lang.reflect.Field;

public class StringInfoAccessor {

    private static Class<?> clazz;
    private static Field stringIndex;

    private Object item;

    public StringInfoAccessor(Object item) {
        this.item = item;
    }

    public int getStringIndex() {
        try {
            return (Integer) stringIndex.get(this.item);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public void setStringIndex(int val) {
        try {
            stringIndex.set(this.item, val);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public static boolean isType(ConstInfoAccessor accessor) {
        return clazz.isAssignableFrom(accessor.getItem().getClass());
    }

    static {
        try {
            clazz = Class.forName("javassist.bytecode.StringInfo");
            stringIndex = clazz.getDeclaredField("string");
            stringIndex.setAccessible(true);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }
}
