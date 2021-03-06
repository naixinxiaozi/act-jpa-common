package act.db.jpa.sql;

/*-
 * #%L
 * ACT JPA Common Module
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.osgl.$;
import org.osgl.util.S;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleWhereExpression extends WhereComponentBase {

    private String columnPrefix;
    private String column;
    private Operator operator;

    public SimpleWhereExpression(String columnPrefix, String column, Operator operator) {
        this(column, operator);
        this.columnPrefix = S.ensure(S.requireNotBlank(columnPrefix).trim()).endWith(".");
    }

    public SimpleWhereExpression(String column, Operator operator) {
        this.column = $.requireNotNull(column);
        this.operator = $.requireNotNull(operator);
    }

    @Override
    public void print(SqlDialect dialect, StringBuilder builder, AtomicInteger ordinalId, String entityAliasPrefix) {
        if (null != columnPrefix) {
            entityAliasPrefix = columnPrefix;
        }
        operator.print(dialect, builder, column, ordinalId, entityAliasPrefix);
    }

}
