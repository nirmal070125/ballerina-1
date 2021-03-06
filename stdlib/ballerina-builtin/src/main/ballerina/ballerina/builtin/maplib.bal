// Copyright (c) 2017 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package ballerina.builtin;

@Description { value:"Removes the specified element from the map"}
@Param { value:"m: The map object" }
@Param { value:"key: The key to be removed" }
public native function <map m>  remove (string key);

@Description { value:"Returns an array of keys contained in the specified map"}
@Param { value:"m: The map object" }
@Return { value:"A string array of keys contained in the specified map " }
public native function <map m> keys () (string[]);

@Description { value:"Check whether specific key exists from the given map"}
@Param { value:"m: The map object" }
@Param { value:"key: The key to be find existence" }
public native function <map m>  hasKey (string key) (boolean);

@Description { value:"Clear the items from given map"}
@Param { value:"m: The map object" }
public native function <map m>  clear ();

@Description { value:"Returns an array of values contained in the specified map"}
@Param { value:"m: The map object" }
@Return { value:"An any array of values contained in the specified map " }
public native function <map m> values () (any[]);
