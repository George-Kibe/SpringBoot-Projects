import { View, Text, TextInput, FlatList } from 'react-native'
import React, {useState} from 'react'
import Entypo from "react-native-vector-icons/Entypo"
import styles from './styles'

import searchResults from '../../../assets/data/search'

const LocationSearchScreen = () => {
  const [inputText, setInputText] = useState("")

  return (
    <View style={styles.container}>
      <View style={styles.searchBox}>
        <Entypo name={"back"} size={35} />
        <TextInput style={styles.textInput} 
            placeholder="Search for Favourite Locations..."
            value={inputText}
            onChangeText={setInputText} />
      </View>
      <FlatList data={searchResults}
        renderItem={({item}) => (
          <View style={styles.row}>
              <View style={styles.iconContainer}>
                  <Entypo name={"location-pin"} size={35} />
              </View>
              <Text style={styles.locationText}>{item.description}</Text>                            
          </View>            
        )}/>
    </View>
  )
}

export default LocationSearchScreen