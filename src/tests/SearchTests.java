package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSendKeys()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ///SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ///SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found to few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        String search_line2 = "sdjkhhvfkjdfbkdfngbjkfd";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        String search_line = "sdjkhhvfkjdfbkdfngbjkfd";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testFindArticlesThenClearSearchField()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Java (programming language)");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title1 = ArticlePageObject.getFirstArticleTitleInList();
        assertEquals(
                "Article title not found",
                "Java (programming language)",
                article_title1
        );

        String article_title2 = ArticlePageObject.getSecondArticleTitleInList();
        assertEquals(
                "Article title not found",
                "JavaScript",
                article_title2
        );

        String article_title3 = ArticlePageObject.getThirdArticleTitleInList();
        assertEquals(
                "Article title not found",
                "Java (software platform)",
                article_title3
        );

        SearchPageObject.clearSearchField();
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.assertThereIsNoResultOfSearch();
        } else {
            ArticlePageObject.titleElementNotFound();
        }
    }

    @Test
    public void testSearchByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertAtLeastThreeArticles();
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (software platform)", "Set of computer software and specifications");
    }
}
