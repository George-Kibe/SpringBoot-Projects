import { GooglePlacesAutocomplete } from 'react-native-google-places-autocomplete';
import { View, Text, TextInput, FlatList, Pressable } from 'react-native'
import React, {useState} from 'react'
import Entypo from "react-native-vector-icons/Entypo"
import styles from './styles'
import { useNavigation } from '@react-navigation/native'


import searchResults from '../../../assets/data/search'

const LocationSearchScreen = () => {
  const navigation = useNavigation()
  const [inputText, setInputText] = useState("")
  const APIKEY="AIzaSyBzQgNWBd_w0HuZkA8UBoAYQ4GauBj52Fk"

  return (
    <View style={styles.container}>
      <View style={styles.searchBox}>
        <GooglePlacesAutocomplete placeholder='Search Properties by Location...'
          onPress={(data, details = null) => {
          // 'details' is provided when fetchDetails = true
          console.warn(data, details);
          }}
          query={{
            key: APIKEY,
            language: 'en',
          }}
        />
        <TextInput style={styles.textInput} 
            placeholder="Search for Favourite Locations..."
            value={inputText}
            onChangeText={setInputText} />        
      </View>
      <FlatList data={searchResults}
        renderItem={({item}) => (
          <Pressable onPress={() =>navigation.navigate("Accommodation Details")}
            style={styles.row}>
              <View style={styles.iconContainer}>
                  <Entypo name={"location-pin"} size={35} />
              </View>
              <Text style={styles.locationText}>{item.description}</Text>                            
          </Pressable>            
        )}/>
    </View>
  )
}

export default LocationSearchScreen