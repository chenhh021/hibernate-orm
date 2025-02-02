/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.community.dialect.unit.lockhint;

import org.hibernate.community.dialect.SybaseASELegacyDialect;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.SybaseASE15Dialect;
import org.hibernate.orm.test.dialect.unit.lockhint.AbstractLockHintTest;

/**
 * @author Gail Badner
 */
public class SybaseASE15LockHintsTest extends AbstractLockHintTest {
	public static final Dialect DIALECT = new SybaseASELegacyDialect();

	protected String getLockHintUsed() {
		return "holdlock";
	}

	protected Dialect getDialectUnderTest() {
		return DIALECT;
	}
}
