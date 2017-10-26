{-# LANGUAGE OverloadedStrings #-}
import Web.Scotty

main = scotty 3000 $ do
  get "/api/deputy/:id" $ do
    id <- param "id"
    html $ mconcat ["Voce esta buscando o deputado de id", id]
  get "/api/party/:name" $ do
    name <- param "name"
    html $ mconcat ["Voce esta buscando o partido de nome ", name]
  