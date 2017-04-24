package org.arquilian.example;

import java.io.PrintStream;

import javax.inject.Inject;

/**
 * A component for creating personal greetings.
 */
public class Greeter {
	public void greet(PrintStream to, String name) {
		to.println(createGreeting(name));
	}

	public String createGreeting(String name) {
		return "Hello, " + name + "!";
	}

	private PhraseBuilder phraseBuilder;

	@Inject
	public Greeter(PhraseBuilder phraseBuilder) {
		this.setPhraseBuilder(phraseBuilder);
	}

	public PhraseBuilder getPhraseBuilder() {
		return phraseBuilder;
	}

	public void setPhraseBuilder(PhraseBuilder phraseBuilder) {
		this.phraseBuilder = phraseBuilder;
	}
}