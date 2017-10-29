factorial :: Integer -> Integer
factorial 0 = 1
factorial n = n  * factorial(n-1)

fibonacci :: Integer -> Integer
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n = fibonacci(n-2) + fibonacci(n-1)

fibonacci' :: Integer -> Integer
fibonacci' value
  | value < 0  = error "Can't calculate negative fibonacci"
fibonacci' 0 = 0
fibonacci' 1 = 1
fibonacci' n = fibonacci(n-2) + fibonacci(n-1)

trd :: (a,b,c) -> c
trd (_, _, c) = c

head' :: [a] -> a
head' [] = error "Can't call head on empty list"
head' (x:_) = x

bmiTell :: Double -> Double -> String
bmiTell weight height
  | bmi <= skinny = "You're underweight"
  | bmi <= normal = "Looking good"
  | bmi <= overweight = "You're overweight"
  | otherwise = "You're obese"
  where bmi  = weight / height ^ 2
        skinny = 18.5
        normal = 25.0
        overweight = 30.0

calcBmis :: [(Double, Double)] -> [Double]
calcBmis xs = [bmi  | (w, h) <- xs, let bmi = w / h ^ 2, bmi > 25.0]

maximaum :: (Ord  a) => [a] -> a
maximaum [] = error "Empty list can't have a maxium"
maximaum [x] = x
maximaum (x:xs) = max x (maximaum xs)

replicate' :: Int -> a -> [a]
replicate' n x
  | n  <= 0 = []
  | otherwise = x : replicate' (n-1) x

take' :: (Num i, Ord i) =>  i -> [a] -> [a]
take' n _
  | n  <= 0 = []
take' _ []  = []
take' n (x:xs) =  x : take' (n-1) xs

reverse' ::  [a] -> [a]
reverse' []  = []
reverse' (x:xs) = reverse' xs ++ [x]

quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) =
  let smallerOrEqual = [a | a <- xs, a <= x]
      larger = [a | a <- xs, a > x]
  in quicksort smallerOrEqual ++ [x] ++ quicksort larger

applyTwice :: (a -> a) -> a -> a
applyTwice f x = f(f x)

zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f(x:xs) (y:ys) = f x y : zipWith' f xs ys

