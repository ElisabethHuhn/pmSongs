README for ParkMobile take home coding example
January 9 2022 by Elisabeth Huhn

The task set for the coding example is:
Use the iTunes search API to create a simple application that
retrieves and displays information about your favorite musician or genre of music.

The Requirements call for:
o One screen that displays a list of songs retrieved from the iTunes API. Any songs will do!
o If a user taps on an item in your list,
  a second screen that shows more in-depth information about the song
o Use Kotlin
The final requirement urges broad use of:
 oo code architecture,
 oo navigation,
 oo user experience, and
 oo visual components.

Currently the app architecture is a typical RecyclerView MVVM architecture with Retrofit Network API calls

The accesses the search Apple Songs API to return a canned search parameter list of songs
The canned search parameter is the example provided by Apple at
https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/SearchExamples.html#//apple_ref/doc/uid/TP40017632-CH6-SW1

Specifically, the search parameter is:
https://itunes.apple.com/search?term=jack+johnson&limit=25

The app "as is" is bare bones. Not terribly exciting visually, not very flexible in the song list returned.
It is meant mostly to illustrate a bare bones RecyclerView MVVM architecture with Retrofit API network access.

Architecture Notes:
1) Retrofit Notes
    A) The ApiService is named SongRetrofitApi
    B) The response to the only api call defined in SongRetrofitApi is separately defined in AppleResponse
        i) if several more APIService calls are defined,
            a) much better class names need to be found
            b) things could get messy fast if there are a lot of additional calls defined
    C) Data is passed from the API Response to the UI Fragment via LiveData in the SharedViewModel
       i)   the fragment registers as observing the LiveData variable, with a lamda defined that will
            execute whenever the LiveData variable changes its value
       ii)  The retrofit api callback transfers information from the API response to the ViewModel LiveData
       iii) When the viewModel LiveData is changed, the lambda executes.
2) MVVM Architecture Notes
    A) The UI architecture is XML, Fragments, and RecyclerViews rather than Compose
    B) ViewBinding and NavHost Graph navigation are used in this app
    C) There is only a single shared ViewModel defined between the Activity and all Fragments
       i) as an app gets bigger, this could get messy. Consider separating into a view model per ui controller
    D) The Repository is poorly named SongFetcher. By all rights, this should only be an internal
       call within a more general SongRepository
3) RecyclerView Notes:
    A) The things needed to set up the RecyclerViewAdapter are defined at the Fragment level. These are:
       i) User Even listeners: onClick, onLongClick, onContextClickListenre, etc
       ii) LiveData observer lambdas for the RecyclerView list updates
       iii) The details of these should probably be in the ViewModel, and just invoked
            from the listeners and observers
       iv) The Adapter is defined in the method setupRecyclerView
       v) The adapter class itself is contained within the Fragment file, rather than in a file of its own
            a) onCreateViewHodler()
            b) onBindViewHolder()
            c) getItemCount()
            d) inner class definition of ViewHolder()

There are some extras that came for free from Android Studio:
o Listeners for Drag
o Listeners for Clip Paste
o Listeners for keyboard CTL F find and CTL Z undo
None of which actually do much more than toast in this app

The obvious next steps are:
1) To show more song details on the Details Fragment

2) To make the UI more visually appealing

3) to provide a more general search parameter input for the User.
I would envision another fragment that would allow the user to input parameter key values
that would then be used to build a URL for the @GET

Apple provides search parameter keys for:
o term
o country
o media
o entity
o attribute
o callback
o limit
o lang
o version
o explicit

4) There is no automatic testing. This flies in the face of TDD, but I look at the app as a
prototype. Prototypes are meant to be build fast and be able to be learned from and
potentially be thrown away. Automatic testing is most useful when code is going to production.
It allows the organization to have a higher level of confidence that new fixes will not introduce
new bugs.

The principal reason I stopped here was time. But I can make a good argument for this being a good
stopping place regardless. Programmer time is expensive. The company does not want me spending
a millisecond coding up something beyond what the company needs to provide value to our customers.
It is not up to me, the programmer, to decide the features or the UI look and feel of the app.

It is up to Product Management to define features and set priorities.
The look and feel is up to UI designers.
What has been provided is enough to get the conversation started.
Then it is up to "the powers that be" to decide how this prototype should be changed.

So my story is that this little prototype should be presented to the "powers that be" to let them
decide how it should be changed and spiffed up. Of course I should bring my input to that
conversation. But as a programmer, I should NOT get too far out ahead of the product managers
with expensive (in terms of my time) bells and whistles that have not yet been authorized.

So I'm stopping here. It fills the requirements given, and is enough for others to define
the priorities of the next steps.

One last little guiding proverb about requirements.
Human beings are not capable of telling you what they want. Whether in their personal life or
in software requirements. What they are capable of telling you is: "That ain't it".
So it is incumbent on us as software professionals to design a process where "That ain't it" is
SUCCESS.

- Show something early and often
- Do not get so engrossed in adding features that are too expensive to throw away if not useful

And one last proverb
If you don't know what success looks like before you start, then it doesn't matter what you do,
when you are done someone *important* will think you failed.

Which gives me another good reason for making a process where "That ain't it" is success,
because we are certain to encounter it.