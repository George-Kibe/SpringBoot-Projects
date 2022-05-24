import { View, Text, FlatList } from 'react-native'
import React, {useState, useEffect} from 'react';
import Property from '../../components/Property';


const SearchResultsScreen = ({properties}) => {
  return (
    <View>
      <FlatList data={properties}
      renderItem={({item}) => <Property property={item}/>}
      />
    </View>
  )
}

export default SearchResultsScreen