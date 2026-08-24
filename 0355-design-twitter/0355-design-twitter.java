class Twitter {

    private int time = 0;

    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<Tweet>> tweetMap;

    class Tweet {
        int tweetId;
        int time;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    class Node {
        Tweet tweet;
        int userId;
        int index;

        Node(Tweet tweet, int userId, int index) {
            this.tweet = tweet;
            this.userId = userId;
            this.index = index;
        }
    }

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {

        tweetMap
            .computeIfAbsent(userId, k -> new ArrayList<>())
            .add(new Tweet(tweetId, time++));

        followMap
            .computeIfAbsent(userId, k -> new HashSet<>())
            .add(userId);
    }

    public List<Integer> getNewsFeed(int userId) {

        List<Integer> result = new ArrayList<>();

        PriorityQueue<Node> maxHeap =
            new PriorityQueue<>(
                (a, b) -> b.tweet.time - a.tweet.time
            );

        Set<Integer> users =
            followMap.getOrDefault(userId, new HashSet<>());

        for (int user : users) {

            List<Tweet> tweets = tweetMap.get(user);

            if (tweets != null && !tweets.isEmpty()) {

                int index = tweets.size() - 1;

                maxHeap.offer(
                    new Node(
                        tweets.get(index),
                        user,
                        index
                    )
                );
            }
        }

        while (!maxHeap.isEmpty() && result.size() < 10) {

            Node current = maxHeap.poll();

            result.add(current.tweet.tweetId);

            int previousIndex = current.index - 1;

            if (previousIndex >= 0) {

                List<Tweet> tweets =
                    tweetMap.get(current.userId);

                maxHeap.offer(
                    new Node(
                        tweets.get(previousIndex),
                        current.userId,
                        previousIndex
                    )
                );
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {

        followMap
            .computeIfAbsent(followerId,
                k -> new HashSet<>())
            .add(followeeId);

        followMap
            .computeIfAbsent(followerId,
                k -> new HashSet<>())
            .add(followerId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (followerId != followeeId) {

            Set<Integer> set =
                followMap.get(followerId);

            if (set != null) {
                set.remove(followeeId);
            }
        }
    }
}