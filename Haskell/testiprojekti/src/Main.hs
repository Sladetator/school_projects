module Main where

data Paketti = 
              Suorakaide Natural Natural Natural
             | Sylinteri Natural Natural 
            deriving (Show, Eq)

--onkoKirje :: Paketti -> Bool
--onkoKirje paketti = case paketti of 
--                      Suorakaide pituus leveys korkeus -> (korkeus <= 2) && (leveys <= 20)  && (pituus <= 20)  
                      
--onkoPikku :: Paketti -> Bool
--onkoPikku paketti = case paketti of
 --                     Suorakaide pituus leveys korkeus -> (korkeus <= 20 && korkeus >= 2) && (leveys <= 20)  && (pituus <= 20) 

--onkoSuuri :: Paketti -> Bool
--onkoSuuri paketti = case paketti of
--                      Suorakaide pituus leveys korkeus -> (korkeus > 20) || (leveys > 20)  || (pituus > 20) 

data PakettiLuokka = Kirje 
                      | PikkuPaketti 
                      | Suurpaketti Natural

                    deriving(Show, Eq)

data Lahetys = Sarkyva Paketti
                      |Ykkosluokka Paketti
                      |Kakkosluokka Paketti
                        deriving (Show, Eq)

hinta :: Lahetys -> Double
hinta lahetys
        = case lahetys of
          Ykkosluokka p -> perus p
          Kakkosluokka p -> perus p * 0.9
       ---   Kakkosluokka p -> let
       ---   ph = perusHinta (luokittele p)
       ---   in ph * 0.9
          Sarkyva p     ->  5 + perus p * 1.2
  where 
    perus p = perusHinta (luokittele p)

luokittele :: Paketti -> PakettiLuokka
luokittele paketti = case paketti of
                      Sylinteri pituus sade 
                        -> Suurpaketti  (pituus * round (pi * (fromIntegral (sade*sade) :: Double)))
                      Suorakaide pituus leveys korkeus
                        | (korkeus <= 2) && (leveys <= 20)  && (pituus <= 20)
                            -> Kirje
                        | (korkeus <= 20 && korkeus >= 2) && (leveys <= 20)  && (pituus <= 20) 
                            -> PikkuPaketti
                        | otherwise
                            -> Suurpaketti  (korkeus * leveys * pituus)


perusHinta :: PakettiLuokka -> Double
perusHinta luokka = case luokka of
                            Kirje 
                              -> 3.90
                            PikkuPaketti 
                              -> 5.95
                            Suurpaketti tilavuus
                              -> 0.001*fromIntegral tilavuus

onkoOikeaPaketti :: PakettiLuokka -> Bool
onkoOikeaPaketti pakettiluokka = case pakettiluokka of
                                  Kirje -> False
                                  PikkuPaketti -> True
                                  Suurpaketti _  -> True

                                

main :: IO ()
main = do
  putStrLn "Hello TIEA341"
