/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.net.http.nativeimpl;

import org.ballerinalang.bre.Context;
import org.ballerinalang.mime.util.HeaderUtil;
import org.ballerinalang.mime.util.MimeUtil;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BString;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;
import org.ballerinalang.util.exceptions.BLangNullReferenceException;
import org.ballerinalang.util.exceptions.BallerinaException;

import static org.ballerinalang.mime.util.Constants.COMMA;
import static org.ballerinalang.mime.util.Constants.PARSER_ERROR;
import static org.ballerinalang.mime.util.Constants.SEMICOLON;

/**
 * Native function to parse header value and get value with parameter map.
 *
 * @since 0.96.1
 */
@BallerinaFunction(
        packageName = "ballerina.net.http",
        functionName = "parseHeader",
        args = {@Argument(name = "headerValue", type = TypeKind.STRING)},
        returnType = {@ReturnType(type = TypeKind.STRING),
                @ReturnType(type = TypeKind.MAP, elementType = TypeKind.STRING),
                @ReturnType(type = TypeKind.STRUCT, structType = "Error")},
        isPublic = true
)
public class ParseHeader extends AbstractNativeFunction {

    @Override
    public BValue[] execute(Context context) {
        String errMsg;
        try {
            String headerValue = getStringArgument(context, 0);
            if (headerValue.contains(COMMA)) {
                headerValue = headerValue.substring(0, headerValue.indexOf(COMMA));
            }
            return getValueAndParamMap(headerValue);
        } catch (BLangNullReferenceException ex) {
            errMsg = PARSER_ERROR + "header value cannot be null";
        } catch (BallerinaException ex) {
            errMsg = PARSER_ERROR + ex.getMessage();
        }
        return getParserError(context, errMsg);
    }

    private BValue[] getValueAndParamMap(String headerValue) {
        String value = headerValue.trim();
        if (headerValue.contains(SEMICOLON)) {
            value = HeaderUtil.getHeaderValue(value);
        }
        return getBValues(new BString(value), HeaderUtil.getParamMap(headerValue));
    }

    private BValue[] getParserError(Context context, String errMsg) {
        return getBValues(null, null, MimeUtil.getParserError(context, errMsg));
    }
}
