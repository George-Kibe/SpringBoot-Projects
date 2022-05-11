import { View, Text, FlatList } from 'react-native'
import React from 'react'
import styles from './styles'
import Property from '../../components/Property';
import feed from '../../../assets/data/feed';

const SearchResultsScreen = () => {
  return (
    <View>
      <FlatList data={feed}
      renderItem={({item}) => <Property property={item}/>}
      />
    </View>
  )
}

export default SearchResultsScreen