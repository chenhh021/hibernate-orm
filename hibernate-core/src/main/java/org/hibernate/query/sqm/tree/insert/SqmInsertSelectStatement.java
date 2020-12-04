/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.query.sqm.tree.insert;

import org.hibernate.query.criteria.JpaCriteriaInsertSelect;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.sqm.NodeBuilder;
import org.hibernate.query.sqm.SqmQuerySource;
import org.hibernate.query.sqm.SemanticQueryWalker;
import org.hibernate.query.sqm.tree.select.SqmQuerySpec;
import org.hibernate.query.sqm.tree.from.SqmRoot;

/**
 * @author Steve Ebersole
 */
public class SqmInsertSelectStatement<T> extends AbstractSqmInsertStatement<T> implements JpaCriteriaInsertSelect<T> {
	private SqmQuerySpec<T> selectQuerySpec;

	public SqmInsertSelectStatement(SqmRoot<T> targetRoot, NodeBuilder nodeBuilder) {
		super( targetRoot, SqmQuerySource.HQL, nodeBuilder );
		this.selectQuerySpec = new SqmQuerySpec<>( nodeBuilder );
	}

	public SqmInsertSelectStatement(Class<T> targetEntity, NodeBuilder nodeBuilder) {
		super(
				new SqmRoot<>(
						nodeBuilder.getDomainModel().entity( targetEntity ),
						null,
						nodeBuilder
				),
				SqmQuerySource.CRITERIA,
				nodeBuilder
		);
		this.selectQuerySpec = new SqmQuerySpec<>( nodeBuilder );
	}

	public SqmQuerySpec getSelectQuerySpec() {
		return selectQuerySpec;
	}

	public void setSelectQuerySpec(SqmQuerySpec selectQuerySpec) {
		this.selectQuerySpec = selectQuerySpec;
	}

	@Override
	public <X> X accept(SemanticQueryWalker<X> walker) {
		return walker.visitInsertSelectStatement( this );
	}

	@Override
	public JpaPredicate getRestriction() {
		// insert has no predicate
		return null;
	}
}
