package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickViewList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesThanDeleteOne()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Set of computer software and specifications");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToSavedList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickViewList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.swipeArticleToDelete(article_title);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title);
        MyListsPageObject.waitForArticleToAppearByTitle("Java (software platform)");
        MyListsPageObject.chooseArticleFromSavedList();
        MyListsPageObject.waitForArticleToAppearByTitle("Java (software platform)");
    }
}
