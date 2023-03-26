package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSendKeys()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Java (programming language)");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

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
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
