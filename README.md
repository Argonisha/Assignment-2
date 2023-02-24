# Assignment-2
For question 2, I think the Queue method would be best.  
## Option 1  
For option one, I think it's ok because the threads get to use the room when they want, but if a big group forms there isnt a concrete way to tell when you're going to get in. Although you CAN go do something else you might never get in.
## Option 2
For option two, I think this could lead to similar problems, although there would be no big group, the threads would still randomly occupy the room, and you could go the entire time never seeing the "AVAILABLE" sign.
## Option 3
For option three, I believe this is the best option. The queue ensures that a guest knows roughly when they should be able to get in.  
If a big group forms, the queue should take care of all the people. The only issue would be that the threads can't go do what they want while waiting.  
# Strategy
The cake problem was relatively easy, I emulated the lightswitch problem that we discussed in class at some point.  

For the vase problem I really struggled, I spent ~18 hours trying to implement a queue.   
In the chapter 7 slides I found the Anderson queue but could'nt figure out how to implement it.
I then tried to move to the Java structure ConcurrentLinkedQueue, and I had a good way to continuously dequeue people, but couldn't manage to get the threads to requeue theirselves one dequeued, if they wanted to.

# Disclaimer
I realize that the MinotaurVase file was commit after 11:59, but my solution doesn't even work 100% correctly so it's pretty obvious I wasn't cheating off anyone.
