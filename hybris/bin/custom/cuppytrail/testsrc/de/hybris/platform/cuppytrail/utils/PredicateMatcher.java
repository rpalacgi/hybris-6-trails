package de.hybris.platform.cuppytrail.utils;

import org.hamcrest.CustomTypeSafeMatcher;

import java.util.function.Predicate;

public class PredicateMatcher<T, P extends Predicate<T>> extends CustomTypeSafeMatcher<T> {

	public static <T, P extends Predicate<T>> PredicateMatcher<T, P> of(String description, Predicate<T> cond) {
		return new PredicateMatcher<T, P>(description, cond);
	}

	public static <T, P extends Predicate<T>> PredicateMatcher<T, P> of(Predicate<T> cond) {
		return new PredicateMatcher<T, P>("Condition has been failed", cond);
	}

	private final Predicate<T> expectedCondition;

	public PredicateMatcher(String description, Predicate<T> expectedCondition) {
		super(description);
		this.expectedCondition = expectedCondition;
	}

	@Override
	protected boolean matchesSafely(T item) {
		return expectedCondition.test(item);
	}

}
