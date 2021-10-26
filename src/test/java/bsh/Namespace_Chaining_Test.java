/*****************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one                *
 * or more contributor license agreements.  See the NOTICE file              *
 * distributed with this work for additional information                     *
 * regarding copyright ownership.  The ASF licenses this file                *
 * to you under the Apache License, Version 2.0 (the                         *
 * "License"); you may not use this file except in compliance                *
 * with the License.  You may obtain a copy of the License at                *
 *                                                                           *
 *     http://www.apache.org/licenses/LICENSE-2.0                            *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing,                *
 * software distributed under the License is distributed on an               *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                    *
 * KIND, either express or implied.  See the License for the                 *
 * specific language governing permissions and limitations                   *
 * under the License.                                                        *
 *                                                                           *
/****************************************************************************/

package bsh;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * <a href="http://code.google.com/p/beanshell2/issues/detail?id=74">Namespace chaining issue</a>
 */
@RunWith(FilteredTestRunner.class)
public class Namespace_Chaining_Test {

    @Test
    public void namespace_nesting() throws UtilEvalError {
        final NameSpace root = new NameSpace( null, "root");
        final NameSpace child = new NameSpace(root, "child");

        root.setLocalVariable("bar", 42, false);
        assertEquals(42, child.getVariable("bar"));

        child.setLocalVariable("bar", 4711, false);
        assertEquals(4711, child.getVariable("bar"));
        assertEquals(42, root.getVariable("bar"));
    }


    @Test
    public void check_ExternalNameSpace() throws Exception {
        final ExternalNameSpace externalNameSpace = new ExternalNameSpace();
        externalNameSpace.setVariable("a", Primitive.NULL, false);
        assertTrue("map should contain variable 'a'", externalNameSpace.getMap().containsKey("a"));
        assertNull("variable 'a' should have value <NULL>", externalNameSpace.getMap().get("a"));
    }

}
