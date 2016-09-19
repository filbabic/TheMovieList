package com.example.filip.movielist.common.extensions

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Created by Filip Babic @cobe
 */


fun RealmObject.delete(from: Realm) {
    from.beginTransaction()
    this.deleteFromRealm()
    from.commitTransaction()
}

fun RealmObject.save(to: Realm) {
    to.beginTransaction()
    to.copyToRealm(this)
    to.commitTransaction()
}

fun List<RealmObject>.saveItems(to: Realm) {
    to.beginTransaction()
    to.copyToRealmOrUpdate(this)
    to.commitTransaction()
}

fun RealmResults<out RealmModel>.deleteItems(from: Realm) {
    from.beginTransaction()
    deleteAllFromRealm()
    from.commitTransaction()
}