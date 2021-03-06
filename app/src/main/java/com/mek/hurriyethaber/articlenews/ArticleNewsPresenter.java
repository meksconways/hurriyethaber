package com.mek.hurriyethaber.articlenews;

import android.util.Log;

import com.mek.hurriyethaber.articlenews.adapter.ArticleNewsAdapter;
import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.mek.hurriyethaber.data.ApiRequester;
import com.mek.hurriyethaber.data.NewsRepository;
import com.mek.hurriyethaber.di.ScreenScope;
import com.mek.hurriyethaber.ui.ScreenNavigator;
import com.mek.hurriyethaber.ui.TabScreenNav;

import javax.inject.Inject;

@ScreenScope
class ArticleNewsPresenter implements ArticleNewsAdapter.ArticleClickListener {


    private final ArticleNewsViewModel viewModel;
    private final ApiRequester requester;
    private final NewsRepository repository;
    private final ScreenNavigator screenNavigator;
    private final TabScreenNav tabScreenNav;

    @Inject
    ArticleNewsPresenter(ArticleNewsViewModel viewModel, ApiRequester requester,
                         NewsRepository repository, ScreenNavigator screenNavigator,
                         TabScreenNav tabScreenNav) {

        this.viewModel = viewModel;
        this.tabScreenNav = tabScreenNav;
        this.requester = requester;
        this.repository = repository;
        this.screenNavigator = screenNavigator;
        loadArticleNews();
    }

    private void loadArticleNews() {

        requester.getArticles()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t) -> viewModel.loadingUpdated().accept(false))
                .doOnEvent((d,t) -> viewModel.refreshUpdated().accept(false))
                .subscribe(viewModel.newsUpdated(), viewModel.onError());
    }

    public void refreshPage(){
        requester.getArticles()
                .doOnSubscribe(__ -> viewModel.refreshUpdated().accept(true))
                .doOnEvent((d,t) -> viewModel.refreshUpdated().accept(false))
                .subscribe(viewModel.newsUpdated(),viewModel.onError());

    }

    private void _loadArticleNews(){
        repository.getArticleNews()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.newsUpdated(), viewModel.onError());
    }

    @Override
    public void onClick(NewsModel model) {

        //screenNavigator.push(model.id()); todo
        tabScreenNav.push(model.id());
    }
}
