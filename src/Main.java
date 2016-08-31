import java.io.File;

import javax.xml.transform.Source;

import org.w3c.dom.Node;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.diff.ElementSelectors;

public class Main {

	public static void main(String[] args) {

		Source strGoldVersion = Input.fromFile(new File("resources/doc1.xml")).build();
		Source strTestVersion = Input.fromFile(new File("resources/doc2.xml")).build();

		//String strGoldVersion = "<address><address1>111, A Street</address1><address2>Apt A</address2><city>Ellicott City</city><zipcode>12345</zipcode></address>";
		//String strTestVersion = "<address><address1>222, A Street</address1><address2>Apt A</address2><city>Ellicott City</city><zipcode>98765</zipcode></address>";

		Diff myDiffSimilar = DiffBuilder
				.compare(strGoldVersion)
				.withTest(strTestVersion)
				.withNodeMatcher(
						new DefaultNodeMatcher(ElementSelectors.byName))
				.checkForSimilar()
				.ignoreWhitespace()
				.build();

		for (Difference diff : myDiffSimilar.getDifferences()) {
			String parentName = diff.getComparison().getControlDetails()
					.getTarget().getParentNode().getLocalName();
			
			System.out.println("Field <" + parentName + "> Old Value: \""
					+ diff.getComparison().getControlDetails().getValue()
					+ "\" New Value: \""
					+ diff.getComparison().getTestDetails().getValue()
					+"\"");
		}
	}
}