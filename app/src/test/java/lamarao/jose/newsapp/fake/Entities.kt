package lamarao.jose.newsapp.fake

import lamarao.jose.newsapp.entities.Article
import lamarao.jose.newsapp.entities.NewsResponse
import lamarao.jose.newsapp.entities.Source


val source = Source("id","name")
val article = Article("Author","Content","description","", source, "Title","www.url.com","www.urlToImage.com")
val news1 = NewsResponse(listOf(article),"status",1)